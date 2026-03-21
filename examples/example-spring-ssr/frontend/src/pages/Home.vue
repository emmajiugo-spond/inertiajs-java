<script setup lang="ts">
import { Link } from '@inertiajs/vue3'

defineProps<{
  auth: { user: { name: string } }
}>()
</script>

<template>
  <div style="max-width: 600px; margin: 40px auto; font-family: sans-serif;">
    <h1>Inertia.js + Java (SSR)</h1>
    <p>Welcome, {{ auth.user.name }}!</p>
    <p>This is a demo application showing Inertia.js with <strong>Server-Side Rendering</strong> working with a Spring Boot backend.</p>
    <p style="color: #059669; font-size: 14px;">View page source to confirm SSR — you'll see pre-rendered HTML instead of an empty div.</p>
    <nav>
      <Link href="/events" style="color: #2563eb;">View Events →</Link>
    </nav>
  </div>
</template>
