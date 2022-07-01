import React from "react"
import ReactDOM from "react-dom"

import App from "./App"


import  '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import '../node_modules/font-awesome/css/font-awesome.min.css'


import { createRoot } from 'react-dom/client';
const container = document.getElementById('root');
const root = createRoot(container);
root.render(<React.StrictMode>
    <App />
</React.StrictMode>,);

// ReactDOM.render(
//     <React.StrictMode>
//             <App />
//     </React.StrictMode>,
//     document.getElementById("root")
// )