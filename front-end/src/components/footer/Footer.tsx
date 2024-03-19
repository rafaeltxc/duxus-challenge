import "./Footer.css";
import { Layout } from "antd";

export default function Footer() {
  const { Footer: AntdFooter } = Layout;

  return (
    <>
      <AntdFooter style={{ 
        textAlign: 'center', 
        display: 'flex',
        justifyContent: 'space-between',
        fontSize: '15px',
      }}>
        <a href="http://localhost:8080/doc" target="_blank">
          Documentação
        </a>
        <div className="about">
          Desenvolvido por
          <a href="https://www.linkedin.com/in/rafaeltxc/" target="_blank">Rafael Costa</a>
        </div>
        <a href="https://github.com/rafaeltxc/duxus-challenge" target="_blank">
          Repositório
        </a>
      </AntdFooter>
    </>
  )
}
