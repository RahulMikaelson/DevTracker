import NavBar from "./components/NavBar"
import Dashboard from "./pages/Dashboard"


function App() {

  return (
    <div>
      <NavBar />
      <div className="px-36">
        <Dashboard />
      </div>
    </div>
  )
}

export default App
