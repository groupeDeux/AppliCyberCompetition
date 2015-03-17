/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$('a[href="#panierTabPanier"]').click(function (e) {
  e.preventDefault();
  $(this).tab('show');
});

$('a[href="#panierTabInformation"]').click(function (e) {
  e.preventDefault();
  $(this).tab('show');
});

$('a[href="#panierTabPaiement"]').click(function (e) {
  e.preventDefault();
  $(this).tab('show');
});

$('a[href="#panierTabTerminerCommande"]').click(function (e) {
  e.preventDefault();
  $(this).tab('show');
});