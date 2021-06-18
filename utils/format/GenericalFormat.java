package utils.format;

public class GenericalFormat {
    public static boolean isValidNumericLength(int numeric, int minimum){
        if(numeric >= minimum){
            return true;
        }else{
            return false;      
        }
    }

    public static boolean isValidStringLength(String word, int mininum){
        if(word.length() >= mininum){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isValidPhrase(String phrase, int minimum){
        String[] splitted = phrase.split(" ");

        if(splitted.length >= 2){
            
            int trueCount = 0;

            //incremente o trueCount se a palavra retornada no splitted tiver caracteres iguais ou maiores que o minimo permitido
            for(int i = 0; i < splitted.length; i++){
                String p = splitted[i];
                if(p.length() >= minimum){
                    
                    trueCount++;
                }
            }
           
            //se todas as palavras tiverem o minimo de caracteres permitido, retorna true, caso contrario retorna false
            if(trueCount == splitted.length){
                return true;
            }else{
                return false;
            }

        }else{
            return false;
        }
    }

    public static boolean isValidEmailFormat(String email){
        
        //verificando se a string contem um arroba
        if(email.contains("@")){
            //split pelo @
            String[] splitter = email.split("@");
             //validando dois arrobas
            if(splitter.length == 2){
                String f1 = splitter[0];
                String f2 = splitter[1];
                //validando quantidade de caracteres antes e apos o arroba
                if(f1.length() < 2 || f2.length() < 2){
                    return false;
                }else{
                    return true;
                }
                
            }else{
                return false;
            }
        }else{
            return false;
        }
       
    }
}
