import { Route, Routes } from 'react-router-dom'

import { DefaultLayout } from '@layouts/default-layout/default-layout.component'
import { Home } from '@pages/home/home.page'

export const Router = (): JSX.Element => (
  <Routes>
    <Route path="/" element={<DefaultLayout />}>
      <Route path="/" element={<Home />} />
    </Route>
  </Routes>
)
