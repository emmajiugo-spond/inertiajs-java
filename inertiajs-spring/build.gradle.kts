plugins {
    id("com.vanniktech.maven.publish")
}

dependencies {
    api(project(":inertiajs-core"))

    compileOnly("org.springframework.boot:spring-boot-starter-web:4.0.4")
    compileOnly("org.springframework.boot:spring-boot-autoconfigure:4.0.4")

    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core:3.27.7")
    testImplementation("org.springframework.boot:spring-boot-starter-test:4.0.4")
    testImplementation("org.springframework.boot:spring-boot-starter-web:4.0.4")
}

mavenPublishing {
    coordinates(project.group.toString(), "inertiajs-spring", project.version.toString())

    pom {
        name.set("Inertia.js Spring Boot Starter")
        description.set("Spring Boot auto-configuration adapter for the Inertia.js Java adapter")
        url.set("https://github.com/emmajiugo/inertiajs-java")

        licenses {
            license {
                name.set("Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("repo")
            }
        }

        developers {
            developer {
                id.set("emmajiugo")
                name.set("Chigbo Ezejuguo")
                email.set("emmajiugo@gmail.com")
                url.set("https://github.com/emmajiugo")
            }
        }

        scm {
            connection.set("scm:git:git://github.com/emmajiugo/inertiajs-java.git")
            developerConnection.set("scm:git:ssh://github.com:emmajiugo/inertiajs-java.git")
            url.set("https://github.com/emmajiugo/inertiajs-java/tree/main")
        }
    }

    signAllPublications()
    publishToMavenCentral(automaticRelease = true)
}
