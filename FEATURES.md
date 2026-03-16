# Future Features

Features not yet implemented, tracked for incremental addition after the MVP.

## Medium Impact

- **Once props** — Props resolved once and cached client-side across navigations. Server checks `X-Inertia-Except-Once-Props` header and omits already-loaded keys.
- **History encryption** — Encrypt sensitive data stored in browser history state. Page object fields: `encryptHistory`, `clearHistory`.
- **Precognition validation** — Real-time validation before form submission using `Precognition` header. Returns 204 on success, 422 on error.

## Lower Priority / Larger Effort

- **SSR (Server-Side Rendering)** — Render Vue/React components on the server for SEO and initial load performance. Complex — requires Node/Deno runtime.
- **Quarkus adapter** (`inertiajs-quarkus`) — Wraps Quarkus `RoutingContext`. Uses `ContainerRequestFilter`.
- **Micronaut adapter** (`inertiajs-micronaut`) — Wraps Micronaut's `HttpRequest`/`MutableHttpResponse`.
## Completed

- **Deferred props** — Props excluded from initial render, loaded via follow-up partial reload. Supports grouping for parallel loading. (`DeferredProp`, `InertiaProps.defer()`)
- **Merge props** — Props that append/prepend/deep-merge with existing client-side data during navigation. Supports `matchOn` for deduplication. (`MergeProp`, `InertiaProps.merge()`, `prepend()`, `deepMerge()`)
- **Vite integration** — Auto-detect asset version from Vite manifest hash. (`ViteManifestVersionResolver`)
- **Project scaffolding CLI** (`npx create-inertiajs-java`) — Interactive initializer that scaffolds the frontend directory and templates inside an existing Java project. Supports Vue/React/Svelte and Spring Boot/Javalin.
- **Javalin adapter** (`inertiajs-javalin`) — Wraps Javalin 7's `Context`. Plugin with before/after middleware.
- **Spring Boot DevTools** — Template re-reading in dev mode via `inertia.cache-templates=false`. (`ClasspathTemplateResolver` cache flag)

## Further Inertia JS
InertiaJs docs (https://inertiajs.com/docs/llms.txt)
