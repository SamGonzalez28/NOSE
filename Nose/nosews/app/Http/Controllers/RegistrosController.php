<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace App\Http\Controllers;

use App\Models\Registros;
use App\Models\Cliente;
use Illuminate\Http\Request;

/**
 * Description of RegistrosController
 *
 * @author alejo
 */
class RegistrosController extends Controller {

    private $external_id;

    public function registrar(Request $request) {
        if ($request->isJson()) {
            $data = $request->json()->all();
            try {
                $cliente = \App\Models\Cliente::where("external_id", $data["cliente"])->first();
                $menu = \App\Models\Menu::where("external_id", $data["menu"])->first();
                if ($cliente == true && $menu == true) {
                    $regi = new Registros();
                    $regi->Cliente()->associate($cliente);
                    $regi->Menu()->associate($menu);
                    $regi->cantidad = $data["cantidad"];
                    $regi->valor = $data["valor"];

                    $regi->external_id = utilidades\UUID::v4();

                    $regi->save();
                    return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
                } else {
                    return response()->json(["mensaje" => "Referencia Incorrecta", "siglas" => "RI"], 400);
                }
            } catch (Exception $ex) {
                return response()->json(["mensaje" => "Faltan Datos", "siglas" => "FD"], 400);
            }
        } else {
            return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 404);
        }
    }

    public function listarporCliente($external_id) {
        $cliente = \App\Models\Cliente::where("external_id", $external_id)->first();


        if ($cliente) {
                $lista = Registros:: where('id_cliente', $cliente->id) 
                        ->join('menu','menu.id', '=', 'registros.id_menu')
                    ->orderBy('fecha', 'des')->get();
            foreach ($lista as $item) {
                $data[] = ["cliente" => $cliente->nombres, "cantidad" => $item->cantidad, "valor" => $item->valor,
                           "descripcion" => $item->descripcion];
            }
            return response()->json($data, 200);
        } else {
            return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
        }
    }

}
