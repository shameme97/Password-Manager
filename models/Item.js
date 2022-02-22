var mongoose = require('mongoose');
var Schema = mongoose.Schema;

// Define collection and schema for Items
var Item = new Schema({ website: { type: String }, websiteUsername: {type: String},  websiteEmail: {type: String}, websitePassword: { type: String } } , { collection: 'items' });

module.exports = mongoose.model('Item', Item);