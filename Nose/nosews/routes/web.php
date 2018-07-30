<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$router->get('/', function () use ($router) {
    return $router->app->version();
}); 

$router->get('test/esto/es/una/pueba','TestController@test');

$router->post('comer/entrar', 'LocalController@inicioSesion');//esta en veremos hasta autenticar el token

$router->post('comer/nuevo', 'LocalController@registrar');
$router->post('comer/cambiar', 'LocalController@modificar');

$router->post('cliente/nuevo', 'ClienteController@registrar');

$router->post('comida/nuevo', 'MenuController@registrar');

$router->post('saldo/nuevo', 'CarteraController@registrar');

$router->post('compra/nuevo', 'RegistrosController@registrar');

$router->post('login/nuevo', 'SesionController@registrar');
