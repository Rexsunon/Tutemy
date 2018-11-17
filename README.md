# Tutemy ![CI status](https://img.shields.io/badge/build-passing-brightgreen.svg)
An android app that diplays free udemy course to download.
This app uses JSON feed from the **Wordpress API** to get courses from http://www.coursesdaddy.com/.


# Requirements
* Any Operating System (ie. MacOS X, Linux, Windows)
* Android Studio
* A little knowledge of Java
* A brain to think ;)

# Usage
* Open android studio and import this project,
* Build the gradle
* Modify to your taste


Available for use with **Wordpress blogs** only!!!.
**All you need to do is**
* Change the blog url u want to get feed from in the Api_Link.java. (Required)
* Make sure the site you want to get feed from isnt blocked by cloudplare (Required)

```java
    public static final String TORRENT_BASE_URL = "http://www.coursesdaddy.com/";
    public static final String GDRIVE_BASE_URL = "http://www.getfreetutorial.com/";
```
# External Libraries
The following External Lbraries were used in the app

| Name | Link |
| ------ | ------ |
| JSoup | http://www.jsoup.org |
| Picasso | https://github.com/square/picasso |
| Gson | https://github.com/google/gson |


# Author(s)
**Olusegun Festus**


# Contribution
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.


# Donate

Buy me a Coffee

| Mode | Link/Wallet|
| ------| ------------|
| Bitcoin | 3GrziPW6Gz6S5mXo2EsiYpafW8vXVq5Rbv|

# License

Free for any kind of **modification**.


Enjoy if you like it:)
