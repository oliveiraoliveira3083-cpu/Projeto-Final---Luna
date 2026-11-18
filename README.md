# Luna

- **Projeto acadêmico:** Projeto Final
- **Disciplina:** Programação Mobile

## Integrantes

- **Nome:**
  - **RGM:**
- **Nome:**
  - **RGM:**
- **Nome:**
  - **RGM:**

---

Aplicativo Android de segurança pessoal para mulheres em situação de vulnerabilidade. A interface é camuflada como um aplicativo utilitário (calendário lunar) e esconde a área de segurança atrás de um gesto oculto e de um PIN.

> **Status: protótipo.** Somente as telas são funcionais. Sensores, serviços em segundo plano, envio de localização e acionamento de autoridades **ainda não foram implementados**.

## Conceito

| Funcionalidade | Descrição | Situação |
|---|---|---|
| Modo camuflagem | Tela inicial "Luna Phases" (fases da lua e calendário). A área de segurança abre com gesto oculto e PIN. | Tela pronta |
| Contatos de emergência | Cadastro de quem será avisado no modo SOS. | Tela pronta, dados em memória |
| Opções de ativação | Agitação (acelerômetro), botão power e comando de voz. | Só interface |
| Gatilho por acelerômetro | Alerta com três agitações bruscas. | Não implementado |
| Serviço em segundo plano | Monitoramento de movimento e envio de geolocalização. | Não implementado |
| Silêncio absoluto | SOS sem feedback visual, sonoro ou tátil. | Não implementado |
| Histórico de alertas | Lista de alertas disparados. | Fora do escopo do protótipo |

## Telas do protótipo

1. **Luna Phases (tela camuflada)**: mostra a fase da lua do dia (calculada pela data), um calendário e o cartão "Configurações".
   - Gesto oculto: manter o cartão "Configurações" pressionado por **3 segundos**, sem vibração nem som.
2. **Área Restrita**: indicador de 4 dígitos e campo numérico. O PIN do protótipo é **`1234`**.
3. **Contatos de Emergência**: lista com Mãe, Polícia (190, destacada em vermelho) e Amiga / Advogada. Permite adicionar, editar e remover (com confirmação).
4. **Opções de Ativação**: switch de agitação com slider de sensibilidade, switch de botão power (3 pressionamentos) e palavra-chave de voz. O microfone e o link "Configurações Gerais" mostram apenas um aviso de "Disponível em breve".

A navegação inferior da área de segurança tem **Início** (volta à tela camuflada), **Config** e **Contatos**.

## Tecnologias

- **Linguagem:** Kotlin
- **UI:** XML com ViewBinding, Material Components (tema claro)
- **Arquitetura do protótipo:** Activities e Fragments, com repositório em memória
- **SDK:** `minSdk` 26 (Android 8.0), `targetSdk` e `compileSdk` 35
- **Build:** Gradle 8.9, Android Gradle Plugin 8.7.3, Kotlin 2.0.21
- **Dependências:** `core-ktx`, `appcompat`, `activity-ktx`, `fragment-ktx` e `material`

## Identidade visual

- **Top bar:** altura fixa de 60dp, fundo `#3F51B5`, texto branco, centralizado, negrito, 20sp.
- **Textos secundários e hints:** cinza `#808080`.
- **Botões:** fundo `#D3D3D3`, `app:cornerRadius="4dp"`, `weightSum="2"` quando há dois botões na mesma linha.
- **Teclado:** telas com `EditText` usam `NestedScrollView` e o tratamento de `WindowInsets` (`systemBars()` e `ime()`) feito em `BaseActivity`, para o teclado empurrar o conteúdo sem cobri-lo.

Os mockups de referência foram usados apenas como layout. O tema escuro deles não foi aplicado.

## Estrutura do projeto

```
mobile/
├── settings.gradle.kts            Nome do projeto e módulos
├── build.gradle.kts               Versões dos plugins (Android e Kotlin)
├── gradle.properties              Configurações do Gradle
├── gradle/wrapper/                Versão do Gradle usada pelo Android Studio
└── app/
    ├── build.gradle.kts           Configuração do módulo (SDKs, ViewBinding, dependências)
    └── src/main/
        ├── AndroidManifest.xml    Declara as 4 telas; só a MainActivity aparece no launcher
        ├── java/com/luna/app/
        │   ├── data/
        │   │   ├── Contact.kt              Modelo do contato
        │   │   └── ContactsRepository.kt   Contatos em memória (buscar, adicionar, editar, remover)
        │   └── ui/
        │       ├── BaseActivity.kt         Edge-to-edge e tratamento de WindowInsets
        │       ├── MainActivity.kt         Tela camuflada e gesto de 3 segundos
        │       ├── MoonPhase.kt            Cálculo da fase da lua
        │       ├── MoonPhaseView.kt        View que desenha a lua
        │       ├── PinActivity.kt          Área Restrita (PIN)
        │       ├── SecurityActivity.kt     Container com navegação inferior
        │       ├── ContactsFragment.kt     Aba Contatos
        │       ├── SettingsFragment.kt     Aba Config (Opções de Ativação)
        │       └── ContactFormActivity.kt  Formulário de novo ou edição de contato
        └── res/
            ├── layout/     activity_main, activity_pin, activity_security,
            │               fragment_contacts, item_contact, fragment_settings,
            │               activity_contact_form
            ├── values/     colors, dimens, strings, styles, themes
            ├── drawable/   ícones vetoriais (ic_*) e fundos (bg_*)
            ├── mipmap-anydpi/  ícone adaptativo do app
            ├── menu/       menu_bottom_nav
            └── color/      nav_item_color
```

## Como executar

### Android Studio
1. **File > Open** e selecione a pasta `mobile` (a que contém `settings.gradle.kts`).
2. Aguarde o **Gradle Sync**. Se pedido, instale o **Android SDK Platform 35** e aceite as licenças.
3. Inicie um emulador em **Device Manager** (API 26 ou superior) ou conecte um celular com Depuração USB.
4. Selecione o módulo **app** e clique em **Run**.

### Gerar o APK
- No Android Studio: **Build > Build APK(s)**.
- O arquivo sai em `app/build/outputs/apk/debug/app-debug.apk`. Arraste-o para a janela do emulador para instalar.

> Requisitos: JDK 17 ou superior (o JDK embutido do Android Studio serve). O emulador precisa ter Android 8.0 ou superior. O Nox, por exemplo, só serve com instância Android 9 ou superior.

### Uso
1. Abra o app **Luna Phases**.
2. Mantenha o cartão "Configurações" pressionado por 3 segundos.
3. Digite o PIN `1234`.
4. Navegue entre **Contatos** e **Config**.

## Limitações do protótipo

- Contatos ficam apenas em memória e são perdidos ao fechar o app.
- O PIN é fixo (`1234`) e não é configurável.
- Nenhuma opção de ativação tem efeito: não há leitura do acelerômetro, do botão power nem de voz.
- Não há envio de localização, SMS, ligação ou chamada às autoridades.
- A dica "Pressione por 3 segundos…" aparece na tela camuflada porque está no layout de referência. Ela revela o segredo do app e deve ser removida em uma versão real.

## Próximos passos sugeridos

1. Persistir contatos, PIN e configurações (Room ou DataStore) e guardar o PIN com hash.
2. Serviço em primeiro plano com leitura do acelerômetro e detecção de três agitações.
3. Envio de localização aos contatos e acionamento das autoridades. Definir antes o meio (SMS, ligação ou backend), considerando as permissões sensíveis e as restrições do Android e da Google Play.
4. Silêncio absoluto no SOS, sem vibração, som nem alteração visível na tela.
5. Tela de Histórico de alertas.
6. Revisar a camuflagem: remover a dica visível e ajustar o nome e o ícone do app.
