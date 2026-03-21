plugins {
    id("org.springframework.boot") version "4.0.4"
    id("io.spring.dependency-management") version "1.1.7"
}

dependencies {
    implementation(project(":inertiajs-spring"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}

// ── npm install ──────────────────────────────────────────────────────

tasks.register<Exec>("npmInstall") {
    description = "Install frontend npm dependencies"
    group = "frontend"
    workingDir = file("frontend")
    commandLine("npm", "install")
    inputs.file("frontend/package.json")
    outputs.dir("frontend/node_modules")
}

// ── Frontend production build ────────────────────────────────────────

tasks.register<Exec>("npmBuild") {
    description = "Build frontend assets for production"
    group = "frontend"
    dependsOn("npmInstall")
    workingDir = file("frontend")
    commandLine("npm", "run", "build")
}

// ── SSR build ────────────────────────────────────────────────────────

tasks.register<Exec>("ssrBuild") {
    description = "Build SSR bundle"
    group = "frontend"
    dependsOn("npmInstall")
    workingDir = file("frontend")
    commandLine("npm", "run", "build:ssr")
}

// ── Dev mode: run Vite + SSR server + Spring Boot ────────────────────

tasks.register<Exec>("dev") {
    description = "Starts Vite dev server, SSR server, and Spring Boot together"
    group = "application"
    dependsOn("npmInstall")
    workingDir = file("frontend")
    commandLine(
        "sh", "-c",
        """
        npm run dev &
        VITE_PID=${'$'}!
        node ssr-server.js &
        SSR_PID=${'$'}!
        sleep 2
        echo "✓ Vite dev server started on http://localhost:5173"
        echo "✓ SSR server started on http://127.0.0.1:13714"
        echo "✓ Starting Spring Boot with dev profile..."
        cd ${projectDir} && ${rootDir}/gradlew :examples:example-spring-ssr:bootRun --args='--spring.profiles.active=dev'
        kill ${'$'}VITE_PID 2>/dev/null
        kill ${'$'}SSR_PID 2>/dev/null
        """.trimIndent()
    )
}

// ── Production build: frontend + SSR + bootJar ──────────────────────

tasks.register("buildProd") {
    description = "Builds frontend assets, SSR bundle, then packages the Spring Boot jar"
    group = "build"
    dependsOn("npmBuild", "ssrBuild")
    finalizedBy(tasks.named("bootJar"))
}
