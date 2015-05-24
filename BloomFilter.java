

import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.Collection;


@SuppressWarnings("serial")
public class BloomFilter<E> implements Serializable 
{
    private BitSet bitset;
    private int bitSetSize;
    private double bitsPerElement;
    private int expectedNumberOfFilterElements; // expected (maximum) number of elements to be added
    private int numberOfAddedElements; // number of elements actually added to the Bloom filter
    private int k; // number of hash functions

    static final Charset charset = Charset.forName("UTF-8"); // encoding used for storing hash values as strings

    static final String hashName = "MD5"; // MD5 gives good enough accuracy in most circumstances. Change to SHA1 if it's needed
    static final MessageDigest digestFunction;
    static { // The digest method is reused between instances
        MessageDigest tmp;
        try 
        {
            tmp = java.security.MessageDigest.getInstance(hashName);
        } 
        catch (NoSuchAlgorithmException e) 
        {
            tmp = null;
        }
        digestFunction = tmp;
    }

    public BloomFilter(double c, int n, int k) 
    {

      this.expectedNumberOfFilterElements = n;
      this.k = k;
      this.bitsPerElement = c;
      this.bitSetSize = (int)Math.ceil(c * n);
      numberOfAddedElements = 0;
      this.bitset = new BitSet(bitSetSize);
    }


    public BloomFilter(int bitSetSize, int expectedNumberOElements) 
    {
        this(bitSetSize / (double)expectedNumberOElements,
             expectedNumberOElements,
             (int) Math.round((bitSetSize / (double)expectedNumberOElements) * Math.log(2.0)));
    }


    public BloomFilter(double falsePositiveProbability, int expectedNumberOfElements) 
    {
        this(Math.ceil(-(Math.log(falsePositiveProbability) / Math.log(2))) / Math.log(2), // c = k / ln(2)
             expectedNumberOfElements,
             (int)Math.ceil(-(Math.log(falsePositiveProbability) / Math.log(2)))); // k = ceil(-log_2(false prob.))
    }




    public static int createHash(String val, Charset charset) 
    {
        return createHash(val.getBytes(charset));
    }


    public static int createHash(String val) 
    {
        return createHash(val, charset);
    }


    public static int createHash(byte[] data) 
    {
        return createHashes(data, 1)[0];
    }


    public static int[] createHashes(byte[] data, int hashes) 
    {
        int[] result = new int[hashes];

        int k = 0;
        byte salt = 0;
        while (k < hashes) 
        {
            byte[] digest;
            synchronized (digestFunction) 
            {
                digestFunction.update(salt);
                salt++;
                digest = digestFunction.digest(data);                
            }
        
            for (int i = 0; i < digest.length/4 && k < hashes; i++) 
            {
                int h = 0;
                for (int j = (i*4); j < (i*4)+4; j++) 
                {
                    h <<= 8;
                    h |= ((int) digest[j]) & 0xFF;
                }
                result[k] = h;
                k++;
            }
        }
        return result;
    }


    @Override
    public boolean equals(Object obj) 
    {
        if (obj == null) 
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final BloomFilter<E> other = (BloomFilter<E>) obj;        
        if (this.expectedNumberOfFilterElements != other.expectedNumberOfFilterElements) 
        {
            return false;
        }
        if (this.k != other.k) 
        {
            return false;
        }
        if (this.bitSetSize != other.bitSetSize) 
        {
            return false;
        }
        if (this.bitset != other.bitset && (this.bitset == null || !this.bitset.equals(other.bitset))) 
        {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 61 * hash + (this.bitset != null ? this.bitset.hashCode() : 0);
        hash = 61 * hash + this.expectedNumberOfFilterElements;
        hash = 61 * hash + this.bitSetSize;
        hash = 61 * hash + this.k;
        return hash;
    }




    public int getK() 
    {
        return k;
    }


    public void clear() 
    {
        bitset.clear();
        numberOfAddedElements = 0;
    }


    public void add(E element) 
    {
       add(element.toString().getBytes(charset));
    }


    public void add(byte[] bytes) 
    {
       int[] hashes = createHashes(bytes, k);
       for (int hash : hashes)
           bitset.set(Math.abs(hash % bitSetSize), true);
       numberOfAddedElements ++;
    }

    public boolean contains(E element) 
    {
        return contains(element.toString().getBytes(charset));
    }


    public boolean contains(byte[] bytes) 
    {
        int[] hashes = createHashes(bytes, k);
        for (int hash : hashes) 
        {
            if (!bitset.get(Math.abs(hash % bitSetSize))) 
            {
                return false;
            }
        }
        return true;
    }

}