package com.github.throyer.brinquedoteca.modules.shared.utils;

public class Templates {
  private Templates () { }
  
  public static class PUBLIC {
    public static final String HOME = "index";
    public static final String LOGIN = "login";
    public static final String CONTACT_US = "contato";
    public static final String LUDIC_OBJECT = "objeto";
    public static final String CORNER = "canto";
  }

  public static class MANAGEMENT {
    private static final String BASE_PATH = "management";
    
    public static class CORNER {
      public static final String LIST = BASE_PATH + "/cantos/canto-list";
      public static final String FORM = BASE_PATH + "/cantos/canto-form";
    }

    public static class LUDIC_OBJECT {
      public static final String LIST = BASE_PATH + "/objetos/objeto-list";
      public static final String FORM = BASE_PATH + "/objetos/objeto-form";
    }

    public static class CURATOR {
      public static final String LIST = BASE_PATH + "/curator/curator-list.page";
      public static final String FORM = BASE_PATH + "/curadores/curador-form";
    }

    public static class USER {
      public static final String PROFILE = "conta";
    }
  }  
}
