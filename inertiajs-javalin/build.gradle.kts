plugins {
    id("com.vanniktech.maven.publish")
}

dependencies {
    api(project(":inertiajs-core"))

    compileOnly("io.javalin:javalin:7.1.0")

    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation("io.javalin:javalin:7.1.0")
}

mavenPublishing {
    coordinates(project.group.toString(), "inertiajs-javalin", project.version.toString())

    pom {
        name.set("Inertia.js Javalin Adapter")
        description.set("Javalin plugin and middleware adapter for the Inertia.js Java adapter")
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
