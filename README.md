# i-Dentificateur

![id_2](https://cloud.githubusercontent.com/assets/9053984/7790715/1b59cad2-02c3-11e5-8ad4-417db2d6c2f6.jpg)

*a small programme to identify the human language of a document or text fragment*


  - Type some Markdown on the left
  - See HTML in the right
  - Magic
  


Markdown is a lightweight markup language based on the formatting conventions that people naturally use in email.  As [John Gruber] writes on the [Markdown site] [1]:

> The overriding design goal for Markdown's
> formatting syntax is to make it as readable
> as possible. The idea is that a
> Markdown-formatted document should be
> publishable as-is, as plain text, without
> looking like it's been marked up with tags
> or formatting instructions.

This text you see here is *actually* written in Markdown! To get a feel for Markdown's syntax, type some text into the left window and watch the results in the right.

### Version

&beta;

### Tech

Dillinger uses a number of open source projects to work properly:

* [AngularJS] - HTML enhanced for web apps!
* [Ace Editor] - awesome web-based text editor
* [Marked] - a super fast port of Markdown to JavaScript
* [Twitter Bootstrap] - great UI boilerplate for modern web apps
* [node.js] - evented I/O for the backend
* [Express] - fast node.js network app framework [@tjholowaychuk]
* [Gulp] - the streaming build system
* [keymaster.js] - awesome keyboard handler lib by [@thomasfuchs]
* [jQuery] - duh

### Installation

Download the project zip file. 

```sh
$ wget https://github.com/h1395010/i-Dentificateur/archive/master.zip
```
Navigate to the directory and unzip it. 
```sh
$ unzip master.zip
```
'*cd*' into the directory with the source files.
```sh
$ cd i-Dentificateur-master/
```


```sh
$ git clone [git-repo-url] dillinger
$ cd dillinger
$ npm i -d
$ mkdir -p public/files/{md,html,pdf}
$ gulp build --prod
$ NODE_ENV=production node app
```

### Usage

Execution of the above commands will invoke the User Interface:

![ui](http://i.stack.imgur.com/aiEUV.png)

**Text Fragment**

Click the radio button adjacent to the word '*Text*', in the corresponding box input a fragment or sentence to assess the language in which it is written. 

**Document**

Indicate the radio button beside the word '*Document*', this action will prompt an local explorer window, navigate to the file of interest and make the selection, this will initiate the evaluation process.


Readmes, how to use them in your own application can be found here:

* [plugins/dropbox/README.md](https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md)
* [plugins/github/README.md](https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md)
* [plugins/googledrive/README.md](https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md)
* [plugins/onedrive/README.md](https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md)

### Development

Want to contribute? Great!

Dillinger uses Gulp + Webpack for fast developing.
Make a change in your file and instantanously see your updates!

Open your favorite Terminal and run these commands.

First Tab:
```sh
$ node app
```

Second Tab:
```sh
$ gulp watch
```

(optional) Third:
```sh
$ karma start
```

### TODO

> a software is never done, you just stop working on it

 - Add support for Chinese (Pinyin) and Russian
 - Programatically enforce specific encoding of stored lists and input text
 - Optimize size of Bloom filters
 - Unify input UI and output UI

License
----

MIT



[john gruber]:http://daringfireball.net/
[@thomasfuchs]:http://twitter.com/thomasfuchs
[1]:http://daringfireball.net/projects/markdown/
[marked]:https://github.com/chjj/marked
[Ace Editor]:http://ace.ajax.org
[node.js]:http://nodejs.org
[Twitter Bootstrap]:http://twitter.github.com/bootstrap/
[keymaster.js]:https://github.com/madrobby/keymaster
[jQuery]:http://jquery.com
[@tjholowaychuk]:http://twitter.com/tjholowaychuk
[express]:http://expressjs.com
[AngularJS]:http://angularjs.org
[Gulp]:http://gulpjs.com
