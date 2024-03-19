import "./Integrante.css";
import { Layout, Card, Form, Input, Select, Button, message, FormProps } from "antd";
import Footer from "../../components/footer/Footer";
import Header from "../../components/header/Header";
import { saveOne } from "../../services/IntegranteService";
import { IntegranteObj } from "../../types/Ambient";

export default function Home() {
  const { Content } = Layout;
  const [form] = Form.useForm();

  const onFinish: FormProps["onFinish"] = async (values: IntegranteObj) => {
    const response = await saveOne(values);
    if (!response) {
      message.error("Integrante não pode ser criado, tente novamente.");
    }
    form.resetFields();
    message.success("Integrante criado com sucesso!");
  };

  const onFinishFailed: FormProps["onFinishFailed"] = () => {
    message.error("Integrante não pode ser criado, tente novamente.");
  };

  return (
    <>
      <Header page='1' />
      <Content style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
        <Card
          title="Cadastro de Integrantes"
          bordered={false}
          size="default"
          style={{
            width: '30%',
            height: '70%',
            textAlign: 'center',
        }}>
          <Form
            style={{
              display: 'flex',
              alignSelf: 'center',
              justifySelf: 'center',
              flexDirection: 'column',
              height: '100%',
              alignItems: 'center', 
              justifyContent: 'flex-start',
            }}
            form={form}
            initialValues={{ remember: true }}
            size="large"
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
            autoComplete="off"
            layout="vertical"
          >
            <Form.Item
              label="Nome"
              name="nome"
              rules={[{ required: true, message: 'Insira o nome do integrante!' }]}
            >
              <Input />
            </Form.Item>

            <Form.Item
              label="Função"
              name="funcao"
              rules={[{ required: true, message: 'Insira o função do integrante!' }]}
            >
              <Input />
            </Form.Item>

            <Form.Item
                name="franquia"
                label="Franquia"
                rules={[{ required: true, message: 'Selecione a franquia!' }]}>
              <Select
                placeholder="Selecione..."
                allowClear
              >
                <Select.Option value="league_of_legends">League of Legends</Select.Option>
                <Select.Option value="fortnite">Fortnite</Select.Option>
                <Select.Option value="counter_strike_2">Counter Strike 2</Select.Option>
                <Select.Option value="apex">Apex</Select.Option>
                <Select.Option value="overwatch">Overwatch</Select.Option>
                <Select.Option value="rocket_league">Rocket League</Select.Option>
                <Select.Option value="valorant">Valorant</Select.Option>
              </Select>
            </Form.Item>

            <Form.Item>
              <Button htmlType="submit" style={{ backgroundColor: '#ED8B01', borderColor: '#F27B14', color: 'white' }}>
                Adicionar
              </Button>
            </Form.Item>
          </Form>
        </Card>
      </Content>
      <Footer />
    </>
  )
}
