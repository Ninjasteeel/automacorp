plugins {
	java
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.emse.spring"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.h2database:h2")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	implementation("org.springframework.boot:spring-boot-starter-security")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.testng:testng:7.1.0")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation(platform("software.amazon.awssdk:bom:2.20.56"))
	implementation("software.amazon.awssdk:s3")
	implementation("software.amazon.awssdk:sso")
	implementation("software.amazon.awssdk:ssooidc")
	implementation(platform("org.apache.logging.log4j:log4j-bom:2.20.0"))
	implementation("org.apache.logging.log4j:log4j-1.2-api")
	testImplementation(platform("org.junit:junit-bom:5.10.0"))
	testImplementation("org.junit.jupiter:junit-jupiter")
	implementation ("software.amazon.awssdk:regions")
	implementation ("software.amazon.awssdk:s3")
	implementation ("software.amazon.awssdk:sqs")
	implementation ("software.amazon.awssdk:sns")
}

tasks.withType<Test> {
	useJUnitPlatform()
}