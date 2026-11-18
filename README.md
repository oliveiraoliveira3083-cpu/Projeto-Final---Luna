# Luna

- **Projeto acadêmico:** Projeto Final
- **Disciplina:** Programação Mobile

## Integrantes

- **Nome:Raphael Ferreira de Oliveira 
  - **RGM: 42912059
- **Nome: Eric crescioni monteiro
  - **RGM: 42831661
- **Nome: Rodrigo dos Santos da Silva
  - **RGM: 42545889

---

Aplicativo Android de segurança pessoal para mulheres em situação de vulnerabilidade. A interface é camuflada como um aplicativo utilitário (calendário lunar) e esconde a área de segurança atrás de um gesto oculto e de um PIN.


# LunaApp

Aplicativo Android desenvolvido como projeto final da disciplina de Programação Mobile.

O LunaApp usa uma tela de informações sobre a lua como interface camuflada. Ao acessar o ícone de configurações, o usuário pode criar uma senha ou autenticar-se e abrir a área restrita com os contatos de emergência.

> **Status:** protótipo funcional para demonstração. Algumas funcionalidades dependem de outros aplicativos do dispositivo e não há monitoramento automático de emergências.

## Funcionalidades

- Tela camuflada com representação da lua e informações de lua cheia, lua nova e próximos eventos.
- Criação e armazenamento local de uma senha de acesso.
- Autenticação por PIN ou senha numérica.
- Cadastro de contatos de emergência com nome, telefone e relação.
- Persistência local dos contatos e da senha.
- Exclusão de contatos cadastrados.
- Ação **Ligar**, que abre o discador Android com o número preenchido.
- Ação **Enviar SOS**, que abre o aplicativo de SMS com uma mensagem de emergência pré-preenchida.


## Requisitos

- Android Studio atualizado
- JDK 11 ou superior
- SDK Android 37
- Dispositivo ou emulador com Android 7.0 (API 24) ou superior

## Como executar

1. Abra o Android Studio.
2. Selecione **Open** e abra a pasta `LunaApp`.
3. Aguarde a sincronização do Gradle.
4. Inicie um emulador ou conecte um dispositivo Android.
5. Execute o módulo `app`.

Pelo terminal, na raiz do projeto:

```powershell
.\gradlew.bat assembleDebug
```

O APK de desenvolvimento será gerado em:

```text
LunaApp\app\build\outputs\apk\debug\app-debug.apk
```

## Primeiro uso

1. Abra o aplicativo.
2. Toque no ícone de configurações da tela de fase lunar.
3. Informe uma senha numérica e confirme-a.
4. Use essa mesma senha para acessar a área de contatos.

Os dados são mantidos no armazenamento privado do aplicativo. Para redefinir manualmente a senha e os contatos durante testes, limpe os dados do app nas configurações do Android ou reinstale-o.

## Estrutura do projeto

```text
LunaApp/
├── app/
│   └── src/
│       ├── main/
│       │   ├── java/com/example/lunaapp/
│       │   │   ├── MainActivity.kt
│       │   │   ├── data/
│       │   │   │   └── SecureStorage.kt
│       │   │   ├── model/
│       │   │   │   └── Contact.kt
│       │   │   └── ui/
│       │   │       ├── auth/
│       │   │       ├── contacts/
│       │   │       ├── moon/
│       │   │       └── theme/
│       │   └── res/
│       ├── androidTest/
│       └── test/
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
└── gradlew.bat
```

## Limitações atuais

- A senha é armazenada em SharedPreferences sem criptografia.
- Não existe acelerômetro, botão físico de emergência ou ativação por voz.
- Não há envio automático de SMS ou localização.
- Os dados dos contatos não usam banco de dados ou backup em nuvem.
- A tela lunar apresenta informações estáticas no protótipo.
- O usuário precisa confirmar a ligação ou o envio da mensagem no aplicativo correspondente.

## Possíveis evoluções

- Armazenar a senha e os contatos com criptografia e mecanismos seguros do Android.
- Implementar confirmação biométrica.
- Adicionar serviços de emergência em segundo plano.
- Detectar agitações ou acionamentos específicos.
- Obter e compartilhar geolocalização com os contatos.
- Enviar SMS automaticamente após validação de permissões e regras da plataforma.
- Aplicar o cálculo real da fase da lua a partir da data atual.
- Adicionar testes unitários e testes de interface.

