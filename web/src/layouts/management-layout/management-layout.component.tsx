import { Sidebar } from '@components/sidebar/sidebar.component'
import { Outlet } from 'react-router-dom'
import { Container } from './management-layout.styles'

export const ManagementLayout = (): JSX.Element => {
  return (
    <Container>
      <Sidebar />
      <Outlet />
    </Container>
  )
}
