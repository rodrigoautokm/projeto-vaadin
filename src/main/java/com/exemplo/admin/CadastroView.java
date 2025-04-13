package com.exemplo.admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;

public class CadastroView extends VerticalLayout {
    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private UsuarioAcessoService usuarioAcessoService;

    public CadastroView() {
        TextField cdEmpresaField = new TextField("Código da Empresa");
        TextField nmEmpresaField = new TextField("Nome da Empresa");
        TextField chaveAcessoField = new TextField("Chave de Acesso");
        TextField cnpjField = new TextField("CNPJ");
        TextField ipBdField = new TextField("IP do Banco de Dados");
        TextField portaBdField = new TextField("Porta do Banco de Dados");
        TextField usuarioBdField = new TextField("Usuário do Banco de Dados");
        PasswordField senhaBdField = new PasswordField("Senha do Banco de Dados");

        Button salvarEmpresaButton = new Button("Cadastrar Empresa", event -> {
            try {
                Empresa empresa = new Empresa();
                empresa.setCdEmpresa(Short.parseShort(cdEmpresaField.getValue()));
                empresa.setNmEmpresa(nmEmpresaField.getValue());
                empresa.setChaveAcesso(chaveAcessoField.getValue());
                empresa.setCnpj(cnpjField.getValue());
                empresa.setIpBd(ipBdField.getValue());
                empresa.setPortaBd(portaBdField.getValue());
                empresa.setUsuarioBd(usuarioBdField.getValue());
                empresa.setSenhaBd(senhaBdField.getValue());

                empresaService.cadastrarEmpresa(empresa);
                Notification.show("Empresa cadastrada com sucesso!");
            } catch (Exception e) {
                Notification.show("Erro ao cadastrar empresa: " + e.getMessage());
            }
        });

        FormLayout empresaForm = new FormLayout(
            cdEmpresaField, nmEmpresaField, chaveAcessoField, cnpjField,
            ipBdField, portaBdField, usuarioBdField, senhaBdField, salvarEmpresaButton
        );

        TextField cdEmpresaUsuarioField = new TextField("Código da Empresa");
        TextField usuarioField = new TextField("Usuário");
        PasswordField senhaUsuarioField = new PasswordField("Senha");

        Button salvarUsuarioButton = new Button("Cadastrar Usuário", event -> {
            try {
                UsuarioAcesso usuario = new UsuarioAcesso();
                Empresa empresa = new Empresa();
                empresa.setCdEmpresa(Short.parseShort(cdEmpresaUsuarioField.getValue()));
                usuario.setEmpresa(empresa);
                usuario.setUsuario(usuarioField.getValue());
                usuario.setSenha(senhaUsuarioField.getValue());

                usuarioAcessoService.cadastrarUsuario(usuario);
                Notification.show("Usuário cadastrado com sucesso!");
            } catch (Exception e) {
                Notification.show("Erro ao cadastrar usuário: " + e.getMessage());
            }
        });

        FormLayout usuarioForm = new FormLayout(
            cdEmpresaUsuarioField, usuarioField, senhaUsuarioField, salvarUsuarioButton
        );

        add(new VerticalLayout(
            new com.vaadin.flow.component.html.H2("Cadastro de Empresa"),
            empresaForm,
            new com.vaadin.flow.component.html.H2("Cadastro de Usuário"),
            usuarioForm
        ));
    }
}