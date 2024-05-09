import React from "react";
import { ApiProvider } from "./api-provider";
import { Guard } from "./guard";
import { Card, CardHeader, ChakraProvider, Heading} from '@chakra-ui/react';

export function App() {
    return <ChakraProvider>
        <ApiProvider>
            <Guard></Guard>
        </ApiProvider>
    </ChakraProvider>
}