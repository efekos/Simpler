> This documentation is cancelled. see the JavaDoc.

<!-- TOC -->
* [Installation](#installation)
  * [Maven](#maven)
  * [Gradle](#gradle)
<!-- TOC -->

# Installation

## Maven

Add this repository
````xml
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
</repositories>
````

Add this dependency to your dependencies.
````xml
<dependency>
	    <groupId>com.github.efekos</groupId>
	    <artifactId>Simpler</artifactId>
	    <version>1.7.1</version>
</dependency>
````

## Gradle

Add this repository
````gradle
allprojects {
	repositories {
		// other repositories
		maven { url 'https://jitpack.io' }
	}
}
````

Add this dependency
````gradle
dependencies {
	 implementation 'com.github.efe124:Simpler:1.7.1'
}
````