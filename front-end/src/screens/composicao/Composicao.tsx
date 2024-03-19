import "./Composicao.css";
import Footer from "../../components/footer/Footer";
import Header from "../../components/header/Header";
import { ComposicaoObj, IntegranteObj, TimeObj } from "../../types/Ambient";
import { Layout, Card, Form, Input, Button, Transfer, TransferProps, FormProps, message, DatePicker } from "antd";
import { useEffect, useState } from "react";
import { findAll } from "../../services/IntegranteService";
import { saveOne } from "../../services/TimeService";
import { saveMany } from "../../services/ComposicaoService";
import { RecordType } from "../../types/Ambient";

export default function Composicao() {
  const [integrantes, setIntegrantes] = useState<IntegranteObj[]>([]);
  const { Content } = Layout;
  const [form] = Form.useForm();
  const mockData: RecordType[] = integrantes.map((item) => ({
    key: item.integranteId!.toString(),
    title: item.nome,
  }));
  const initialTargetKeys = mockData.filter((item) => Number(item.key) > 10).map((item) => item.key);
  const [targetKeys, setTargetKeys] = useState<string[]>(initialTargetKeys);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response: IntegranteObj[] = await findAll();
        setIntegrantes([...response]);
      } catch (error) {
        console.log(error);
      }
    }

    fetchData();
  }, []);

  const onFinish: FormProps["onFinish"] = async (values) => {
    if (!values.data) {
      const data: Date = new Date();
      const dataFormatada: string = data.toISOString().slice(0, 10);

      values = {
        ...values,
        data: dataFormatada,
      }
    }

    const time: TimeObj = {
      nome: values.nome,
      data: values.data
    } as TimeObj

    const timeResponse: TimeObj = await saveOne(time);
    if (!timeResponse) {
      message.error("Time não pode ser criado, tente novamente.");
    }

    message.success("Time criado com sucesso!");

    const itArray: ComposicaoObj[] = [];
    values.integrantes.map((id: number) => {
      itArray.push({
        integrante: {
            integranteId: id,
        },
        time: {
            timeId: timeResponse!.timeId,
        }
      })
    });

    const compResponse: ComposicaoObj[] = await saveMany(itArray);
    if (!compResponse) {
      message.error("Composição não pode ser criada, tente novamente.");
    }

    message.success("Composição criada com sucesso!");
    form.resetFields();
    setTargetKeys([]);
  };

  const onFinishFailed: FormProps["onFinishFailed"] = () => {
    message.error("Composição não pode ser criada, tente novamente.")
  };

  const handleChangeTransfer: TransferProps['onChange'] = (newTargetKeys) => {
    setTargetKeys(newTargetKeys);
  };

  return (
    <>
      <Header page='2' />
      <Content style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
        <Card
          title="Criação de Times"
          bordered={false}
          size="small"
          style={{
            width: '30%',
            height: '80%',
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
              label="Nome do time"
              name="nome"
              rules={[{ required: true, message: 'Insira o nome do do time!' }]}
            >
              <Input />
            </Form.Item>

            <Form.Item
              label="Data de criação"
              name="data"
            >
              <DatePicker
                format="YYYY-MM-DD"
                value={new Date()}
                style={{ width: '100%'}} />
            </Form.Item>

            <Form.Item
              name="integrantes"
              label="Integrantes"
              rules={[{ required: true, message: 'Selecione ao menos um integrante!' }]}>
              <Transfer
                dataSource={mockData}
                targetKeys={targetKeys}
                onChange={handleChangeTransfer}
                render={(item) => item.title}
                oneWay
                style={{ marginBottom: 16 }}
              />
            </Form.Item>

            <Form.Item>
              <Button htmlType="submit" style={{
                backgroundColor: '#ED8B01',
                borderColor: '#F27B14',
                color: 'white',
                marginTop: '15px',
              }}>
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
