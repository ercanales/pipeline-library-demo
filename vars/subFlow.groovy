def call(Map config) {
  
    TestAuth(
        token: "${config.SA_TOKEN}",
        project: "${config.PROJECT_CONFIG}"
        //project: "x"
    )

}
