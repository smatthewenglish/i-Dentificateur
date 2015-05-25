# i-Dentificateur

![id_2](https://cloud.githubusercontent.com/assets/9053984/7790715/1b59cad2-02c3-11e5-8ad4-417db2d6c2f6.jpg)

*a small programme to identify the human language of a document or text fragment*

The methodoligies used herein were selected on the basis of:

> Garg, Archana, Vishal Gupta, and Manish Jindal. "*A Survey of Language Identification Techniques and Applications*." Journal of Emerging Technologies in Web Intelligence 6.4 (2014): 388-400.

### How does it work?
  - The programme stores a dictionary of each language for which it is operable in the form of a [Bloom filter](http://billmill.org/bloomfilter-tutorial/)
  - For an input text we search through the Bloom filters for each language incrementing an associated count whenever a word in the text corresponds to a word in one of our dictionaries. 
  - Finally, the language of the dictionary with the highest valued representive count variable is determined the winner. 

This method is surprisingly effective, able to correctly identify such potentially perplexing text fragments as "*Schwarzenegger in Kindergarten Cop*". 
  


### Version

&beta;

### Tech

To work properly:

* You should be running Java 7 or higher.

### Installation

Download the project zip file. 

```sh
$ wget https://github.com/h1395010/i-Dentificateur/archive/master.zip
```
Navigate to the directory and unzip it. 
```sh
$ unzip master.zip
```
'*cd* ' into the directory with the source files.
```sh
$ cd i-Dentificateur-master/
```
Compile with the command
```sh
$ javac *.java
```
Run with the command 
```sh
$ java iDentificateur
```


### Usage

Execution of the above commands will invoke the User Interface:

![ui](http://i.stack.imgur.com/aiEUV.png)

**Text Fragment**

Click the radio button adjacent to the word '*Text*', in the corresponding box input a fragment or sentence to assess the language in which it is written. 

**Document**

Indicate the radio button beside the word '*Document*', this action will prompt an local explorer window, navigate to the file of interest and make the selection, this will initiate the evaluation process.


### Development

Want to contribute? Great!

Pull-requests are quite welcome.

Alternatively if you want to see a new feature let me know!

### TODO

> a software is never done, you just stop working on it

 - Add support for Chinese (Pinyin) and Russian
 - Programatically enforce specific encoding of stored lists and input text
 - Optimize size of Bloom filters
 - Unify input UI and output UI

License
----

MIT



