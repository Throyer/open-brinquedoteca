import { Route, Routes } from 'react-router-dom'

import { DefaultLayout } from '@layouts/default-layout/default-layout.component'
import { Home } from '@pages/home/home.page'
import { ManagementLayout } from '@layouts/management-layout/management-layout.component'
import { Corners } from '@pages/management/corners/corners.page'
import { LudicObjects } from '@pages/management/ludic-objects/ludic-objects.page'
import { Curators } from '@pages/management/curators/curators.page'
import { Login } from '@pages/login/login.page'
import { Corner } from '@pages/corner/corner.page'
import { LudicObject } from '@pages/ludic-object/ludic-object.page'

export const Router = (): JSX.Element => (
  <Routes>
    <Route path="/" element={<DefaultLayout />}>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/canto" element={<Corner />} />
      <Route path="/objeto-ludico" element={<LudicObject />} />
      <Route path="gerenciamento" element={<ManagementLayout />}>
        <Route path="cantos" element={<Corners />} />
        <Route path="objetos-ludicos" element={<LudicObjects />} />
        <Route path="curadores" element={<Curators />} />
      </Route>
    </Route>
  </Routes>
)
