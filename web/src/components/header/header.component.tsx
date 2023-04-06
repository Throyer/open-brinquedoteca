import { Scroll, Timer } from 'phosphor-react'
import { NavLink } from 'react-router-dom'
import { HeaderContainer } from './header.styles'
import { logo } from '../../assets'

export const Header = (): JSX.Element => {
  return (
    <HeaderContainer>
      <img src={logo} />
      <nav>
        <NavLink to="/" title="Timer">
          <Timer size={24} />
        </NavLink>
        <NavLink to="/corner" title="Histórico">
          <Scroll size={24} />
        </NavLink>
      </nav>
    </HeaderContainer>
  )
}
