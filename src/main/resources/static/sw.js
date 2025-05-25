self.addEventListener('install', (event) => {
    console.log('Service Worker instalado');
});

self.addEventListener('fetch', (event) => {
    console.log('Fetch interceptado:', event.request.url);
    event.respondWith(fetch(event.request));
});