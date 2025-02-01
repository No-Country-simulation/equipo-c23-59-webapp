import type React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

type FormMode = "login" | "registro";

export default function Loginregistro() {

    const [formMode, setFormMode] = useState<FormMode>("login");

    const API_URL = import.meta.env.VITE_API_URL;

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        nombre: "",
        apellido: "",
        email: "",
        password: "",
        confirmPassword: "",
    });

    const [error, setError] = useState("");

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");

        if (formMode === "registro" && formData.password !== formData.confirmPassword) {
            setError("Las contraseñas no coinciden");
            return;
        }
        try {
            const endpoint = formMode === 'login'
                ? `${API_URL}/auth/login`
                : `${API_URL}/auth/registro`;

            const body = formMode === "login" ? {
                email: formData.email,
                password: formData.password
            } : {
                nombre: formData.nombre,
                apellido: formData.apellido,
                email: formData.email,
                password: formData.password
                
            };
console.log("Enviando JSON:", JSON.stringify(body));
            const response = await fetch(endpoint, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(body)
            })
            const data = await response.json();
 console.log("Respuesta del servidor:", data);

            if (!response.ok) {
                throw new Error(data.message || "Error en la solicitud");
            }

            localStorage.setItem("token", data.token);
            navigate("/home");
        } catch (error: any) {
            console.error("Error en la solicitud:", error);
            setError(error.message || "Error inesperado");
        }

    }

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const gradients = {
        login: "from-blue-400 to-purple-500",
        registro: "from-purple-500 to-blue-400",
    };

    return (
        <div
            className={`min-h-screen min-w-screen w-full flex items-center justify-center
        bg-gradient-to-br ${gradients[formMode]}`}
        >
            <div className="w-full max-w-md mx-4">
                <div className="bg-white/10 backdrop-blur-md rounded-3xl shadow-xl p-8 transition-all duration-500 ease-in-out">
                    <h1 className="text-3xl font-light text-center text-white mb-8">
                        {formMode === "login" ? "INGRESAR" : "REGISTRO"}
                    </h1>

                    <div className="flex rounded-full bg-white/20 p-1 mb-8">
                        <button
                            className={`flex-1 py-2 px-4 rounded-full text-sm font-medium transition-colors duration-300
                ${formMode === "login" ? "bg-white text-purple-500" : "text-white/80 hover:text-white"}`}
                            onClick={() => setFormMode("login")}
                        >
                            INGRESAR
                        </button>
                        <button
                            className={`flex-1 py-2 px-4 rounded-full text-sm font-medium transition-colors duration-300
                ${formMode === "registro" ? "bg-white text-purple-500" : "text-white/80 hover:text-white"}`}
                            onClick={() => setFormMode("registro")}
                        >
                            REGISTRATE
                        </button>
                    </div>
                    {error && (
                        <div className="text-red-300 text-center mb-4 text-sm">
                            {error}
                        </div>
                    )}
                    <p className="text-white/90 text-center mb-6">
                        {formMode === "login"
                            ? "Ingresa tu usuario y contraseña"
                            : "Completa tus datos para registrarte"}
                    </p>

                    <div
                        className={`transition-all duration-500 ease-in-out ${formMode === "login" ? "h-[200px]" : "h-[400px]"
                            }`}
                    >
                        <form onSubmit={handleSubmit} className="space-y-4">
                            <input
                                type="email"
                                name="email"
                                value={formData.email}
                                onChange={handleChange}
                                placeholder="CORREO ELECTRÓNICO"
                                className="w-full p-4 rounded-lg bg-white/10 border border-white/20 text-white 
                  placeholder:text-white/50 focus:outline-none focus:border-white/40"
                                required
                            />

                            <div
                                className={`transition-all duration-500 ease-in-out ${formMode === "registro"
                                    ? "opacity-100 max-h-[200px]"
                                    : "opacity-0 max-h-0"
                                    } overflow-hidden`}
                            >
                                <input
                                    type="text"
                                    name="nombre"
                                    value={formData.nombre}
                                    onChange={handleChange}
                                    placeholder="NOMBRE"
                                    className="w-full p-4 rounded-lg bg-white/10 border border-white/20 text-white 
                    placeholder:text-white/50 focus:outline-none focus:border-white/40 mb-4"
                                    required={formMode === "registro"}
                                    tabIndex={formMode === "registro" ? 0 : -1}
                                />
                                <input
                                    type="text"
                                    name="apellido"
                                    value={formData.apellido}
                                    onChange={handleChange}
                                    placeholder="APELLIDO"
                                    className="w-full p-4 rounded-lg bg-white/10 border border-white/20 text-white 
                    placeholder:text-white/50 focus:outline-none focus:border-white/40"
                                    required={formMode === "registro"}
                                    tabIndex={formMode === "registro" ? 0 : -1}
                                />
                            </div>

                            <input
                                type="password"
                                name="password"
                                value={formData.password}
                                onChange={handleChange}
                                placeholder="CONTRASEÑA"
                                className="w-full p-4 rounded-lg bg-white/10 border border-white/20 text-white 
                  placeholder:text-white/50 focus:outline-none focus:border-white/40"
                                required
                            />

                            <div
                                className={`transition-all duration-500 ease-in-out ${formMode === "registro"
                                    ? "opacity-100 max-h-[80px]"
                                    : "opacity-0 max-h-0"
                                    } overflow-hidden`}
                            >
                                <input
                                    type="password"
                                    name="confirmPassword"
                                    value={formData.confirmPassword}
                                    onChange={handleChange}
                                    placeholder="CONFIRMAR CONTRASEÑA"
                                    className="w-full p-4 rounded-lg bg-white/10 border border-white/20 text-white 
                    placeholder:text-white/50 focus:outline-none focus:border-white/40"
                                    required={formMode === "registro"}
                                    tabIndex={formMode === "registro" ? 0 : -1}
                                />
                            </div>

                            <div className="pt-4">
                                <button
                                    type="submit"
                                    className="w-16 h-16 mx-auto flex items-center justify-center rounded-full
                    bg-white hover:bg-white/90 transition-colors duration-300 shadow-lg"
                                >
                                    <svg
                                        className="w-6 h-6 text-white"
                                        fill="none"
                                        strokeLinecap="round"
                                        strokeLinejoin="round"
                                        strokeWidth="2"
                                        viewBox="0 0 24 24"
                                        stroke="currentColor"
                                    >
                                        <path d="M5 12h14M12 5l7 7-7 7" />
                                    </svg>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}
