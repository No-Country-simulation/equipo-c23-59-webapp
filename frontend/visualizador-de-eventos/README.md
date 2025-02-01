# 🎟️ Visualizador de Eventos

Este es el frontend de la **Plataforma de Eventos Locales**, desarrollado con **React + Vite + TypeScript**.

## 🚀 Tecnologías Utilizadas
- **React** con Vite ⚡
- **TypeScript** para tipado seguro
- **React Router** para navegación
- **Tailwind CSS** para estilos rápidos y modernos
- **ESLint** para mantener código limpio

## 📂 Estructura del Proyecto
```
frontend/
 ├── visualizador-de-eventos/
 │   ├── public/                 # Archivos públicos (favicon, index.html)
 │   ├── src/                    # Código fuente
 │   │   ├── assets/             # Imágenes, íconos, etc.
 │   │   ├── components/         # Componentes reutilizables
 │   │   ├── view/               # Páginas principales (Home, Login, etc.)
 │   │   ├── App.tsx             # Componente raíz
 │   │   ├── main.tsx            # Punto de entrada
 │   │   ├── index.css           # Estilos globales
 │   │   ├── vite-env.d.ts       # Configuración de Vite
 │   ├── .env.example            # Plantilla de variables de entorno
 │   ├── .gitignore              # Archivos a ignorar en Git
 │   ├── package.json            # Dependencias del proyecto
 │   ├── tsconfig.json           # Configuración de TypeScript
 │   ├── vite.config.ts          # Configuración de Vite
 ├── node_modules/               # Dependencias instaladas
 ├── README.md                   # Documentación del proyecto
```

## 📦 Instalación y Uso

### 1️⃣ Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/tu-repositorio.git
cd equipo-c23-59-webapp/frontend/visualizador-de-eventos
```

### 2️⃣ Instalar dependencias
```bash
npm install
```
### 3️⃣ Configurar las variables de entorno
VITE_API_URL=http://localhost:8080 o la url que este usando el back


### 3️⃣ Iniciar el servidor de desarrollo
```bash
npm run dev
```
La aplicación se ejecutará en `http://localhost:5173/`.

## 🌍 Rutas Configuradas
| Ruta | Componente |
|------|-----------|
| `/` | Home.tsx |
| `/login` | Login.tsx |

## 🛠️ Comandos Útiles
| Comando | Descripción |
|---------|------------|
| `npm run dev` | Inicia el servidor de desarrollo |
| `npm run build` | Compila para producción |
| `npm run lint` | Corre ESLint para revisar el código |

---
