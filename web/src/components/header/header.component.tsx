import { Cube, HouseLine, Pinwheel } from 'phosphor-react'
import { NavLink } from 'react-router-dom'
import { HeaderContainer } from './header.styles'

export const Header = (): JSX.Element => {
  return (
    <HeaderContainer>
      <Pinwheel size={40} />
      <nav>
        <NavLink to="/" title="Timer">
          <HouseLine size={24} />
        </NavLink>
        <NavLink to="/gerenciamento/cantos" title="Histórico">
          <Cube size={24} />
        </NavLink>
      </nav>
    </HeaderContainer>
  )
}
