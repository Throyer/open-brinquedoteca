import { Outlet } from 'react-router-dom'

import { Header } from '@components/header/header.component'

import { LayoutContainer } from './default-layout.styles'

export const DefaultLayout = (): JSX.Element => {
  return (
    <LayoutContainer>
      <Header />
      <Outlet />
    </LayoutContainer>
  )
}
