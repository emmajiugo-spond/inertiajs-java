plugins {
    id("com.vanniktech.maven.publish")
}

dependencies {
    api("com.fasterxml.jackson.core:jackson-databind:2.21.1")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.21.1")

    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core:3.27.7")
}

mavenPublishing {
    coordinates(project.group.toString(), "inertiajs-core", project.version.toString())

    pom {
        name.set("Inertia.js Java Core")
        description.set("Core protocol engine for the Inertia.js Java adapter — framework-agnostic, zero dependencies beyond Jackson")
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
