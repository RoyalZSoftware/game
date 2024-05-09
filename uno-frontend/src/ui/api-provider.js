import { createContext, useContext, useState } from "react";

const apiContext = createContext();

export function ApiProvider({children}) {
    const [token, setToken] = useState("");
    return <apiContext.Provider value={{token, setToken}}>{children}</apiContext.Provider>
}

export function useApiProvider() {
    return useContext(apiContext);
}