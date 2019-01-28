package com.acepabdurohman.belajaroauth2jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final String CLIENT_ID = "client-id";
    private final String CLIENT_SECRET = "client-secret";
    private final String PRIVATE_KEY = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpQIBAAKCAQEA0U8km9PtwdJ4In5knE+p1kIxpGywma3Sp3nFAyrCRoi7vycw\n" +
            "hwD3wq/iJ+Kw04Fh1dMu9cM0Xfwy8YI2TcWSc3Avbi0scfUeYvn3/xXUXJ2KXplV\n" +
            "btrONSIZLWl2jp/b7GJF0KxMxkGb2lDl3Df4vMccWB9sI9axGkDmTmUZsKUSrOgD\n" +
            "viLHzXoGhQyTngfsq3wuW0JHfps7a0QQrIBszN7PDCyzIbf5DB4hf1unEeQx3by1\n" +
            "cvhMIh731iJlKztAKcyhX7d8pgeNbvNAawia1puNITVDELTGsmyAGl0ZNbPlyexC\n" +
            "nXCobFNlQOmu1Qvj5grBLG351Kg1/ElKD3AYpwIDAQABAoIBAQCh6fAu3zV4DyFB\n" +
            "xbZ8uJ9wEEqooC/I4g8n4OnHdmdM+u8Jgv5mIRiIMVjoc9NH+jk9mJ0mHng/EB40\n" +
            "dv8dEG4nAKygNS/Bg9WayTXPzQo3DuOAJGtTUs3bimIhoAjHceBFrwvEzdSiNRSk\n" +
            "QJpfzaiMGBbUEJKt8qGT5oqCMHE8FD8Rw70a3RVBwiwFUBEdHqR4A3i7uSog7H01\n" +
            "w3SUuIszP/3dgYelk3FckEtnU5535wIrZwXLM8QrYSWoncXJMoWU+O5k35N40hUS\n" +
            "hd0EI7duDFByvNkUG/gpWtCxKyT5x5wJ/pCPmJxDtcvMhd1zWQZ5jx1qBAxwBYTd\n" +
            "w7/3NCkBAoGBAPkaXOSIrMnp1bb+S6PsqO8xN+/xHZBqKfMvff9JjHL65x8yJPgT\n" +
            "mHykPJ7L6q5nntnXaG3wlJTGJPUCgQrO6+F56uGGz9WpGvgT36ZKrENcUMsJLPUl\n" +
            "1H7zZ03OlQ6MZT16TNcytNAdMbZTgw7csyqtcvGInutHADf/7gkHUT2BAoGBANca\n" +
            "uOWzatoJrankSDVGiF0ZRiTMBooY9XMvJsBDgOFqncC4S/xHygBfScezYW4AD7JK\n" +
            "GVq9/gUywUsschd2qZJ34QuwNx3CAKVj6J34Gf31nWcRwmNvIAZwHX//3c4CydVe\n" +
            "5Q5SUCCeLYbxZErG7wqJeYN52WqWT7xTA8VbYLonAoGBAPAQdwuLqDHi16LGibEN\n" +
            "53S00zq6/QnrNkh2MZAwmllI8io/Uh9+Cwu73+wNizrPA+jeznZ9T0PdfUpeQmxf\n" +
            "JDYlH7Z/DJbVpPcFeBkzh7ZXJkm1qw0KizJZcuoqN75QkVThI2KVLGoQaJNVAk31\n" +
            "ZfmwNMEPMFCAGXbef9FVqvaBAoGADHZi4gQMG8fVxPMR7+w/OE2fj8A2XMfnaJ4B\n" +
            "u7/tdqHrs9+5n0s1e+IJb0WITow4XJcIcwZw8jInmshfb9aTKoZBhqDWAKZdzTdF\n" +
            "oele8CTL0s3nmkU08MqMGv9jBW2J7g/Ka5L/S3R+pMPVkLOH4+9lF+X2X8OHqRyS\n" +
            "KlwJ9Z8CgYEAwCoSY30LR8Z6ItUFgC+4G1C0sx7nADBYEElYltGk8K/yzfrEYaEn\n" +
            "D8xPgsCFluc1bF9AHFfsMY0/sznA4mGlztRTQc7dr23plbZkWhbKRbOhKC/TBPk8\n" +
            "sB/dzLEZ9PzxIQC/PYN/tScT9J4IyxRoXOWO5NMzfq6nrAxyGatZw7U=\n" +
            "-----END RSA PRIVATE KEY-----";

    private final String PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0U8km9PtwdJ4In5knE+p\n" +
            "1kIxpGywma3Sp3nFAyrCRoi7vycwhwD3wq/iJ+Kw04Fh1dMu9cM0Xfwy8YI2TcWS\n" +
            "c3Avbi0scfUeYvn3/xXUXJ2KXplVbtrONSIZLWl2jp/b7GJF0KxMxkGb2lDl3Df4\n" +
            "vMccWB9sI9axGkDmTmUZsKUSrOgDviLHzXoGhQyTngfsq3wuW0JHfps7a0QQrIBs\n" +
            "zN7PDCyzIbf5DB4hf1unEeQx3by1cvhMIh731iJlKztAKcyhX7d8pgeNbvNAawia\n" +
            "1puNITVDELTGsmyAGl0ZNbPlyexCnXCobFNlQOmu1Qvj5grBLG351Kg1/ElKD3AY\n" +
            "pwIDAQAB\n" +
            "-----END PUBLIC KEY-----\n";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(PRIVATE_KEY);
        converter.setVerifierKey(PUBLIC_KEY);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(CLIENT_ID).secret(CLIENT_SECRET).scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(3600)
                /*.refreshTokenValiditySeconds(3600 * 24)*/
        ;

    }
}
