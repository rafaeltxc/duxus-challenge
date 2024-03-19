import './App.css';
import { Layout } from 'antd';
import { Route, Routes } from 'react-router-dom';
import Integrante from './screens/integrante/Integrante';
import Composicao from './screens/composicao/Composicao';

function App() {
  return (
    <Layout className='layout'>
      <Routes>
        <Route path='/' Component={Integrante} />
        <Route path='/composicao' Component={Composicao} />
      </Routes>
    </Layout>
  )
}

export default App
