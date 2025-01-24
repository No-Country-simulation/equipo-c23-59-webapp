# Proyecto Plataforma de Eventos Locales

## Estructura de Ramas
- `main`: Código estable listo para producción.
- `develop`: Rama de desarrollo donde se integran todas las funcionalidades.
- `feature/...`: Ramas de funcionalidades específicas.

## Flujo de Trabajo
1. Crear ramas a partir de `develop` para cada tarea.
2. Subir los cambios y abrir un Pull Request hacia `develop`.
3. Revisar y aprobar los Pull Requests antes de fusionarlos.
4. Fusionar `develop` con `main` solo cuando esté listo para producción.

## Estructura del Proyecto
├── backend/        # Código del backend (Spring Boot, etc.)
├── frontend/       # Código del frontend (React)
├── docs/           # Documentación (guías, diagramas, etc.)
└── README.md       # Descripción del proyecto