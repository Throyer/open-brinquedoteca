import { Sidebar } from '@components/sidebar/sidebar.component'
import { Outlet } from 'react-router-dom'

export const ManagementLayout = (): JSX.Element => {
  return (
    <aside>
      <Sidebar />
      <Outlet />
    </aside>
  )
}
