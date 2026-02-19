import { BrowserRouter, Route, Navigate, Routes } from "react-router-dom"
import { QueryClient, QueryClientProvider } from "@tanstack/react-query"
import { Toaster } from "react-hot-toast"

import { DarkModeProvider } from "./context/DarkModeContext";

import GlobalStyles from "./styles/GlobalStyles"
import AppLayout from "./ui/AppLayout"
import Ventas from "./pages/Ventas"
import PageNotFound from "./pages/PageNotFound" // Need to create this or stub

const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            staleTime: 0,
        },
    },
});

function App() {
    return (
        <DarkModeProvider>
            <QueryClientProvider client={queryClient}>
                <GlobalStyles />
                <BrowserRouter>
                    <Routes>
                        <Route element={<AppLayout />}>
                            <Route index element={<Navigate replace to='ventas' />} />
                            <Route path="ventas" element={<Ventas />} />
                            <Route path="dashboard" element={<h1>Dashboard (Placeholder)</h1>} />
                            <Route path="reservas" element={<h1>Reservas (Placeholder)</h1>} />
                            <Route path="rooms" element={<h1>Rooms (Placeholder)</h1>} />
                            <Route path="users" element={<h1>Users (Placeholder)</h1>} />
                            <Route path="settings" element={<h1>Settings (Placeholder)</h1>} />
                            <Route path="account" element={<h1>Account (Placeholder)</h1>} />
                            <Route path="clientes" element={<h1>Clientes (Placeholder)</h1>} />
                        </Route>

                        <Route path="*" element={<h1>Page Not Found</h1>} />
                    </Routes>
                </BrowserRouter>

                <Toaster
                    position="top-center"
                    gutter={12}
                    containerStyle={{ margin: "8px" }}
                    toastOptions={{
                        success: {
                            duration: 3000,
                        },
                        error: {
                            duration: 5000,
                        },
                        style: {
                            fontSize: "16px",
                            maxWidth: "500px",
                            padding: "16px 24px",
                            backgroundColor: "var(--color-grey-0)",
                            color: "var(--color-grey-700)",
                        },
                    }}
                />
            </QueryClientProvider>
        </DarkModeProvider>

    )
}

export default App
