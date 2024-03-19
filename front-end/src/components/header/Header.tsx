import './Header.css';
import { Layout, Menu, MenuProps } from 'antd';

export default function Header(props: {page: string}) {
  type MenuItem = Required<MenuProps>['items'][number];

  const { Header: AntdHeader } = Layout;

  function getItem(
    label: React.ReactNode,
    key: React.Key,
    link: string
  ): MenuItem {
    const content = link ? <a href={link}>{label}</a> : label;
    
    return {
      key,
      label: content,
    } as MenuItem;
  }

  const items: MenuItem[] = [
    getItem('Integrante', '1', 'http://localhost:5173/'),
    getItem('Composição', '2', 'http://localhost:5173/composicao'),
  ]

  return (
    <>
      <AntdHeader
        style={{
          position: 'sticky',
          top: 0,
          zIndex: 1,
          width: '100%',
          height: '65px',
          display: 'flex',
          justifyContent: 'flex-start'
        }}
      >
      <Menu
        theme="dark"
        mode="horizontal"
        defaultSelectedKeys={[props.page]}
        items={items}
        style={{
            width: '100%',
            display: 'flex',
            minWidth: 0,
            fontSize: '15px'
          }}
      />
      <img src='/Duxus.png' alt="Élin Duxus" className='dxImg' />
      </AntdHeader>
    </>
  )
}
