import http from 'node:http'
import { readFileSync } from 'node:fs'
import { fileURLToPath } from 'node:url'
import { dirname, resolve } from 'node:path'

const __dirname = dirname(fileURLToPath(import.meta.url))

// In dev mode, use Vite to load the SSR module dynamically.
// In production, load the pre-built SSR bundle.
const isDev = process.env.NODE_ENV !== 'production'

let render

if (isDev) {
  // Dynamic import via Vite's ssrLoadModule for HMR support
  const { createServer } = await import('vite')
  const vite = await createServer({
    server: { middlewareMode: true },
    appType: 'custom',
  })

  const mod = await vite.ssrLoadModule(resolve(__dirname, 'src/ssr.ts'))
  render = mod.default
} else {
  // Production: load the pre-built SSR bundle
  const mod = await import(resolve(__dirname, 'dist/ssr/ssr.js'))
  render = mod.default
}

const port = parseInt(process.env.SSR_PORT || '13714', 10)

const server = http.createServer(async (req, res) => {
  if (req.method === 'POST' && req.url === '/render') {
    let body = ''
    req.on('data', (chunk) => { body += chunk })
    req.on('end', async () => {
      try {
        const page = JSON.parse(body)
        const result = await render(page)
        const response = JSON.stringify({
          head: result.head || [],
          body: result.body || '',
        })
        res.writeHead(200, { 'Content-Type': 'application/json' })
        res.end(response)
      } catch (err) {
        console.error('SSR render error:', err)
        res.writeHead(500, { 'Content-Type': 'application/json' })
        res.end(JSON.stringify({ error: err.message }))
      }
    })
  } else {
    res.writeHead(404)
    res.end()
  }
})

server.listen(port, '127.0.0.1', () => {
  console.log(`Inertia SSR server running on http://127.0.0.1:${port}`)
})
