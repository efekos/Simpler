[![](https://jitpack.io/v/efekos/Simpler.svg)](https://jitpack.io/#efekos/Simpler)
[![License: MIT](https://flat.badgen.net/badge/Lisence/MIT)](https://opensource.org/licenses/MIT)
![version](https://flat.badgen.net/github/release/efekos/Simpler)
![](https://flat.badgen.net/github/stars/efekos/Simpler)
[![](https://flat.badgen.net/badge/JavaDoc/available/green)](https://efekos.github.io/Simpler)
![](https://flat.badgen.net/badge/Tutorials/unavaliable/red)
# Simpler

<!-- TOC -->
* [Simpler](#simpler)
  * [Installation](#installation)
    * [Maven](#maven)
    * [Gradle](#gradle)
<!-- TOC -->

Simpler is an API that helps you make spigot plugins with a lot of different ways. With Simpler, you can make

* Custom commands
* Custom items
* Custom menus
* Custom config files (Even .JSON ones!)
* Custom data using .JSON files (meaning no database needed!)

## Installation

### Maven

* Add this repository:
````xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
````
* Add this dependency
````xml
<dependency>
    <groupId>com.github.efekos</groupId>
    <artifactId>Simpler</artifactId>
    <version>1.6.5</version>
</dependency>
````

### Gradle

* Add this repository:
````gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
````
* Add this dependency:
````gradle
dependencies {
	implementation 'com.github.efekos:Simpler:1.6.5'
}
````