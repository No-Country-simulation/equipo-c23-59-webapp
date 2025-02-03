import react from '@vitejs/plugin-react';
import { defineConfig, loadEnv } from 'vite';

export default defineConfig(({ mode }) => {
  // Cargar variables de entorno según el modo (dev, prod, etc.)
  const env = loadEnv(mode, process.cwd(), '');

  return {
    plugins: [react()],
    server: {
      proxy: {
        '/auth': {
          target: env.VITE_API_URL, // Ahora se usa `env.VITE_API_URL`
          changeOrigin: true
        }
      }
    }
  };
});
