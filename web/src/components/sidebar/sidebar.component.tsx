import { Alien, Cube, GraduationCap } from 'phosphor-react'
import { NavLink } from 'react-router-dom'

import * as S from './sidebar.styles'

export const Sidebar = () => {
  return (
    <S.Container>
      <NavLink to="/gerenciamento/cantos" title="Timer">
        <Cube size={24} />
      </NavLink>
      <NavLink to="/gerenciamento/objetos-ludicos" title="Histórico">
        <Alien size={24} />
      </NavLink>
      <NavLink to="/gerenciamento/curadores" title="Histórico">
        <GraduationCap size={24} />
      </NavLink>
    </S.Container>
  )
}
