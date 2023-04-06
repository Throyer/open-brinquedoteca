import { Scroll, Timer } from 'phosphor-react'
import { NavLink } from 'react-router-dom'

export const Sidebar = () => {
  return (
    <nav>
      <NavLink to="/gerenciamento/cantos" title="Timer">
        <Timer size={24} />
      </NavLink>
      <NavLink to="/gerenciamento/objetos-ludicos" title="Histórico">
        <Scroll size={24} />
      </NavLink>
    </nav>
  )
}
