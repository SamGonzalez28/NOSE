<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace App\Http\Controllers;

use App\Models\Registros;
use Illuminate\Http\Request;

/**
 * Description of RegistrosController
 *
 * @author alejo
 */
class RegistrosController extends Controller{
    private $external_id;

    public function listar() {
        $lista = \App\Models\Registros::where('id_local', $local)->get();
        $data = array();
        foreach ($lista as $item) {
            $data[] = ["cliente" => $item->id_cliente, "menu" => $item->id_menu,
                "cantidad" => $item->cantidad, "cantidad" => $item->cantidad, "fecha"=>$item->created_at->format("Y-m-d")
                    ,"valor" => $item->valor,"saldo actual" => $item->saldo_actual, "saldo final" => $item->saldo_final];
        }
        return $data;
    }

    public function registrar(Request $request) {
        if ($request->isJson()) {
            $data = $request->json()->all();
            try {
                $cliente = \App\Models\Cliente::where("external_id", $data["clave"])->first();
                $menu = \App\Models\Menu::where("external_id", $data["id"])->first();
                if ($local) {
                    $regi = new Registros();
                    $regi->Cliente()->associate($cliente);
                    $regi->Menu()->associate($menu);
                    $regi->cantidad = $data["cantidad"];
                    $regi->valor = $data["valor"];
                    $regi->saldo_actual = $data["saldo_actual"];
                    $regi->saldo_final = $data["saldo_final"];
                    $regi->external_id = utilidades\UUID::v4();

                    $regi->save();
                }

                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            } catch (Exception $ex) {
                return response()->json(["mensaje" => "Faltan Datos", "siglas" => "FD"], 400);
            }
        } else {
            return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 404);
        }
    }
}
