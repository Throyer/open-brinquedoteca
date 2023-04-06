import { ThemeProvider } from 'styled-components'
import { defaultTheme } from './styles/themes/default'
import { BrowserRouter } from 'react-router-dom'
import { GlobalStyle } from './styles/global'
import { Router } from './routes/Router'

export const App = (): JSX.Element => (
  <ThemeProvider theme={defaultTheme}>
  <BrowserRouter>
    <Router />
  </BrowserRouter>
  <GlobalStyle />
</ThemeProvider>
)
