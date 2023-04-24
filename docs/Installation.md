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
	    <groupId>com.github.efe124</groupId>
	    <artifactId>Simpler</artifactId>
	    <version>0.0.1-snapshot</version>
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
	 implementation 'com.github.efe124:Simpler:0.0.1-snapshot'
}
````