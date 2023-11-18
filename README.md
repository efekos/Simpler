[![](https://jitpack.io/v/efekos/Simpler.svg)](https://jitpack.io/#efekos/Simpler)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![version](https://img.shields.io/badge/version-1.5.8-blue)
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
    <groupId>com.github.efekos</groupId>
    <artifactId>Simpler</artifactId>
    <version>1.5.8</version>
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
	implementation 'com.github.efekos:Simpler:1.5.8'
}
````