def call(Map config) {
  
    TestAuth(
        token: "${config.SA_TOKEN}",
      project: config.PROJECT_CONFIG.getAt(GCP_PROJECT, 5)
        //project: "x"
    )

}
