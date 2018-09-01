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
$router->post('cliente/entrar', 'ClienteController@inicioSesion');//esta en veremos hasta autenticar el token

$router->post('comer/nuevo', 'LocalController@registrar');
$router->post('comer/cambiar/{external_id}', 'LocalController@modificar');
$router->post('comer/borrar/{external_id}', 'LocalController@eliminar');
$router->get('comer/listar', 'LocalController@listar');
$router->get('comer/ver/{external_id}', 'LocalController@ver');

$router->post('cliente/nuevo', 'ClienteController@registrar');
$router->post('cliente/cambiar/{external_id}', 'ClienteController@modificar');
$router->post('cliente/borrar/{external_id}', 'ClienteController@eliminar');
$router->get('cliente/listar', 'ClienteController@listar');
$router->get('cliente/ver/{external_id}', 'ClienteController@ver');

$router->post('comida/nuevo', 'MenuController@registrar');
$router->post('comida/cambiar/{external_id}', 'MenuController@modificar');
$router->post('comida/borrar/{external_id}', 'MenuController@eliminar');
$router->get('comida/listar/comer/{external_id}', 'MenuController@listarporLocal');
$router->get('comida/listar', 'MenuController@listar');
$router->get('comida/ver/{external_id}', 'MenuController@ver');

$router->post('saldo/nuevo', 'CarteraController@registrar');
$router->post('saldo/cambiar/{external_id}', 'CarteraController@modificar');
$router->get('saldo/listar', 'CarteraController@listar');

$router->post('compra/nuevo', 'RegistrosController@registrar');
$router->get('compra/listar/cliente/{external_id}', 'RegistrosController@listarporCliente');
$router->get('compra/listar/comer/{external_id}', 'RegistrosController@listarporLocal');
$router->get('compra/listar', 'RegistrosController@listar');

$router->post('login/nuevo', 'SesionController@registrar');
$router->post('login/borrar/{external_id}', 'SesionController@eliminar');
$router->get('login/listar/comer/{external_id}', 'SesionController@listarporLocal');