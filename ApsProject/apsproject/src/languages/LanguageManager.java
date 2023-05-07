package apsproject.src.languages;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {  

    private static LanguageManager instance;

    private ResourceBundle messages;

    private String lang, locale;
    
    public LanguageManager() {

        //=> Definindo o idioma padrao.
        setLanguage("en","en");

    }

    //=> Metodo responsavel por retornar uma unica instancia da classe LanguageManager para toda a aplicacao.
    public static LanguageManager getInstance() {
        if (instance == null) {
            instance = new LanguageManager();
        }
        return instance;
    }

    //=> Metodo responsaver por atualizar o idioma selecionado, recebe o codigo do idioma e localidade
    public void updateLanguage(String selectedLang, String selectedLocale) {

        lang = selectedLang;
        locale = selectedLocale;
        setLanguage(lang, locale);
    }

    //=> Metodo responsaver por definir qual o idoma sera usado no programa, recebe nome do arquivo e localidade
    public void setLanguage(String messageFile, String languageCode) {
        messages = ResourceBundle.getBundle("apsproject.src.languages." + messageFile, Locale.of(languageCode));
    }

    //=> Metodo responsaver por atualizar os meus textos/strings, receber a chave e retorna a string com o texto traduzido
    public String getString(String key) {
        return messages.getString(key);
    }

}
