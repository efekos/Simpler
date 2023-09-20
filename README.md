[![](https://jitpack.io/v/efe124/Simpler.svg)](https://jitpack.io/#efe124/Simpler)
[![License: ISC](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/ISC)
![version](https://img.shields.io/badge/version-1.5.6-blue)
# Simpler

<!-- TOC -->
* [Simpler](#simpler)
  * [Installation](#installation)
    * [Maven](#maven)
    * [Gradle](#gradle)
<!-- TOC -->


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
    <groupId>com.github.efe124</groupId>
    <artifactId>Simpler</artifactId>
    <version>1.5.6</version>
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
	implementation 'com.github.efe124:Simpler:1.5.6'
}
````